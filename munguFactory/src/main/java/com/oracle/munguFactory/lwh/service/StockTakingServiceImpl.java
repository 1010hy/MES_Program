package com.oracle.munguFactory.lwh.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.oracle.munguFactory.dto.FactoryDTO;
import com.oracle.munguFactory.dto.ItemDTO;
import com.oracle.munguFactory.dto.StockTakingDTO;
import com.oracle.munguFactory.lwh.dao.StockTakingDAO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockTakingServiceImpl implements StockTakingService {

	private final StockTakingDAO st;
	
	@Override
	public List<FactoryDTO> selectFactoryList() {
		return st.selectFactoryList();
	}

	@Override
	public List<ItemDTO> selectItemList(StockTakingDTO stockTakingDTO) {
		return st.selectItemList(stockTakingDTO);
	}

	@Override
	public List<StockTakingDTO> selectStockTakingList(StockTakingDTO stockTakingDTO) {
		return st.selectStockTakingList(stockTakingDTO);
	}

	@Override
	public List<StockTakingDTO> selectSubulList() {
		return st.selectSubulList();
	}

	@Override
	public List<StockTakingDTO> selectItemInfo(StockTakingDTO stockTakingDTO) {
		return st.selectItemInfo(stockTakingDTO);
	}

	@Override
	@Transactional
	public int insertStockTaking(StockTakingDTO stockTakingDTO) {
		
		try {
			st.insertStockTaking(stockTakingDTO);
			st.updateStockCnt(stockTakingDTO);
			
			return 1;
		} catch (Exception e) {
			System.out.println("insert Error : " + e.getMessage());
			return 0;
		}
		
	}

	@Override
	public int totalStockTakingCnt() {
		return st.totalStockTakingCnt();
	}

}