package com.servicesystem.api.application.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.servicesystem.api.application.payload.insert.CategoryInsert;
import com.servicesystem.api.application.payload.response.CategoryResponse;
import com.servicesystem.api.application.payload.update.CategoryUpdate;
import com.servicesystem.api.domain.exceptions.BusinessException;
import com.servicesystem.api.domain.exceptions.ObjectNotFoundException;
import com.servicesystem.api.domain.models.Category;
import com.servicesystem.api.domain.repositories.CategoryRepository;
import com.servicesystem.api.domain.utils.ConverterUtil;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
	private ModelMapper modelMapper;

    public Page<CategoryResponse> findAllByName (String name, Pageable pageable){

        Page<Category> page = categoryRepository.findAllByNameContainingIgnoreCase(name, pageable);
        return page.map(category -> modelMapper.map(category, CategoryResponse.class));
    }

    public CategoryResponse findById (String id){
		
		Category category = categoryRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Categoria não encontrada! Id "+ id ));

		return modelMapper.map(category, CategoryResponse.class);
	}

	@Transactional
	public CategoryResponse create (CategoryInsert categoryInsert){

		if(!categoryInsert.getName().isBlank() && existsByName(categoryInsert.getName()))
			throw new BusinessException("O nome fornecido para a categoria já está em uso.");

		return modelMapper.map(
			categoryRepository.save(modelMapper.map(categoryInsert, Category.class)), CategoryResponse.class
		);
	}

    @Transactional
	public CategoryResponse update (String id, CategoryUpdate categoryUpdate) {

		if(!categoryUpdate.getName().isBlank() && existsByName(categoryUpdate.getName()))
			throw new BusinessException("O nome fornecido para a categoria já está em uso.");

		Category searchedCategory = categoryRepository.findById(ConverterUtil.convertStringForUUID(id))
        .orElseThrow(() -> new ObjectNotFoundException(
            "Categoria não encontrada! Id "+ id ));

			updateCategory(categoryUpdate, searchedCategory);
		
		return modelMapper.map(categoryRepository.save(searchedCategory), CategoryResponse.class);
	}

    public void delete (String id) {
		CategoryResponse searchedCategory = findById(id);
		categoryRepository.deleteById(searchedCategory.getId());
	}

    public boolean existsByName(String name){
        return categoryRepository.existsByName(name);
    }

    private void updateCategory (CategoryUpdate categoryUp, Category category) {

        if (categoryUp.getName() != null)
            category.setName(categoryUp.getName());

        if(categoryUp.getDescription() != null)
            category.setDescription(categoryUp.getDescription());

        if(categoryUp.getPrice() != null)
            category.setPrice(categoryUp.getPrice());
        
    }
}
