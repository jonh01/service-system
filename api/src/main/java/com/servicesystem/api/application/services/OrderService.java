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
import com.servicesystem.api.domain.repositories.OrderRepository;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
	private ModelMapper modelMapper;

    public Page<OrderResponse> findAllByServiceUserId (String userId, Pageable pageable){

        Page<Order> page = orderRepository.findAllByServiceProvidedUser_Id(ConverterUtil.convertStringForUUID(userId), pageable);
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
        
    }

}
