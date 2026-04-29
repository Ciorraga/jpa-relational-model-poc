package com.pocsma.jparelationalmodel.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pocsma.jparelationalmodel.app.dto.StoreDTO;
import com.pocsma.jparelationalmodel.app.mapper.StoreMapper;
import com.pocsma.jparelationalmodel.app.model.Store;
import com.pocsma.jparelationalmodel.app.repository.StoreRepository;

@ExtendWith(MockitoExtension.class)
class StoreQueryServiceImplTest {

	@Mock
	private StoreRepository storeRepository;

	@Mock
	private StoreMapper storeMapper;

	@InjectMocks
	private StoreQueryServiceImpl storeQueryService;

	@Test
	void shouldReturnMappedStores() {
		Store store = new Store();
		store.setStoreId(1);

		StoreDTO storeDTO = new StoreDTO(1, "Store 1", null);

		when(storeRepository.findAll()).thenReturn(List.of(store));
		when(storeMapper.toDto(store)).thenReturn(storeDTO);

		List<StoreDTO> result = storeQueryService.findAll();

		assertThat(result).containsExactly(storeDTO);
		verify(storeRepository).findAll();
		verify(storeMapper).toDto(store);
	}
}
