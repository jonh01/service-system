package com.servicesystem.api.application.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.insert.OrderInsert;
import com.servicesystem.api.application.payload.response.OrderResponse;
import com.servicesystem.api.application.payload.update.OrderUpdate;
import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.Order;
import com.servicesystem.api.domain.models.enums.StatusService;
import com.servicesystem.api.domain.repositories.OrderRepository;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ServiceProvidedService providedService;

    @Autowired
    private UserService userService;

    @Autowired
	private ModelMapper modelMapper;

    public Page<OrderResponse> findAllByServiceUserId (String userId, Pageable pageable){

        Page<Order> page = orderRepository.findAllByServiceProvidedUserId(ConverterUtil.convertStringForUUID(userId), pageable);
        return page.map(order -> modelMapper.map(order, OrderResponse.class));
    }

    public Page<OrderResponse> findAllByUserId (String userId, Pageable pageable){

        Page<Order> page = orderRepository.findAllByUserId(ConverterUtil.convertStringForUUID(userId), pageable);
        return page.map(order -> modelMapper.map(order, OrderResponse.class));
    }

    public Page<OrderResponse> findAllByUserIdAndEndAt (String userId, boolean finished, Pageable pageable){

        Page<Order> page;
        if(finished)
            page = orderRepository.findAllByUserIdAndEndAtIsNotNull(ConverterUtil.convertStringForUUID(userId), pageable);
        else
            page = orderRepository.findAllByUserIdAndEndAtIsNull(ConverterUtil.convertStringForUUID(userId), pageable);

        return page.map(order -> modelMapper.map(order, OrderResponse.class));
    }

    public OrderResponse findById (String id){
		
		Order order = orderRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Ordem não encontrada! Id "+ id ));

		return modelMapper.map(order, OrderResponse.class);
	}

	@Transactional
	public OrderResponse create (OrderInsert orderInsert){


        if(orderInsert.getUser().getId() != null && !userService.existsById(orderInsert.getUser().getId().toString())){
            throw new BusinessException("Não é possível criar uma nova Ordem. Este usuário não existe.");
        }

        if(orderInsert.getUser().getId() != null && orderInsert.getServiceProvided().getId() != null && providedService.existsByIdAndUserId(orderInsert.getServiceProvided().getId().toString(), orderInsert.getUser().getId().toString()))
            throw new BusinessException("Não é possível criar uma ordem no seu próprio serviço.");

        if (orderInsert.getUser().getId() != null && orderInsert.getServiceProvided().getId() != null && existsByUserIdAndServiceProvidedIdAndEndAtIsNull(orderInsert.getUser().getId().toString(), orderInsert.getServiceProvided().getId().toString()))
            throw new BusinessException("Não é possível criar uma nova Ordem pois o usuário já possui uma ativa.");

        if(orderInsert.getServiceProvided().getId() != null && !providedService.existsByIdAndStatus(orderInsert.getServiceProvided().getId().toString(), StatusService.Active)){
            throw new BusinessException("Não é possível criar uma nova Ordem pois o serviço não está ativo ou não existe.");
        }

		return modelMapper.map(
			orderRepository.save(modelMapper.map(orderInsert, Order.class)), OrderResponse.class
		);
	}

    @Transactional
	public OrderResponse update (String id, OrderUpdate orderUpdate) {

        if (orderUpdate.getEndAt() != null && !existsWithEndAtNull(id))
            throw new BusinessException("Não é possível editar uma ordem com data final cadastrada.");

		Order searchedOrder = orderRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Ordem não encontrada! Id "+ id ));

			updateOrder(orderUpdate, searchedOrder);
		
		return modelMapper.map(orderRepository.save(searchedOrder), OrderResponse.class);
	}

    public void delete (String id) {
		OrderResponse searchedOrder = findById(id);
		orderRepository.deleteById(searchedOrder.getId());
	}

    public boolean existsById(String id){

        return orderRepository.existsById(ConverterUtil.convertStringForUUID(id));
    }

    public boolean existsByUserIdAndServiceProvidedIdAndEndAtIsNull(String userId, String serviceId){
        return orderRepository.existsByUserIdAndServiceProvidedIdAndEndAtIsNull(ConverterUtil.convertStringForUUID(userId), ConverterUtil.convertStringForUUID(serviceId));
    }

    private boolean existsWithEndAtNull (String id) {
        return orderRepository.existsByIdAndEndAtIsNull(ConverterUtil.convertStringForUUID(id));
    }

    private void updateOrder (OrderUpdate orderUp, Order order) {

        if (orderUp.getStartAt() != null)
            order.setStartAt(orderUp.getStartAt());

        if(orderUp.getEndAt() != null)
            order.setEndAt(orderUp.getEndAt());

        if(orderUp.getPrice() != null)
            order.setPrice(orderUp.getPrice());

        if(orderUp.getLocal() != null)
            order.setLocal(orderUp.getLocal());
        
    }

}
