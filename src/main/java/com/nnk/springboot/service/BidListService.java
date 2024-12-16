package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

        private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList save(BidList bidlist) {
        return bidListRepository.save(bidlist);
    }

//    public void delete(Long bidlist) {
//        bidListRepository.delete(bidlist);
//    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public BidList update(Integer id, BidList bidlist) {
        Optional<BidList> existingBidList = bidListRepository.findById(bidlist.getBidListId());
        if (existingBidList.isPresent()) {
            BidList existingBid = existingBidList.get();
            existingBid.setBidQuantity(bidlist.getBidQuantity());
            return bidListRepository.save(existingBid);
        } throw new IllegalArgumentException("BidList not found");

    }
}
