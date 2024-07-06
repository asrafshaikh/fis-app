package com.tech.home.app.service;

import com.tech.home.app.domain.TransactionDetail;
import com.tech.home.app.repository.TransactionDetailRepository;
import com.tech.home.app.service.dto.TransactionDetailDTO;
import com.tech.home.app.service.mapper.TransactionDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TransactionDetail}.
 */
@Service
@Transactional
public class TransactionDetailService {

    private static final Logger log = LoggerFactory.getLogger(TransactionDetailService.class);

    private final TransactionDetailRepository transactionDetailRepository;

    private final TransactionDetailMapper transactionDetailMapper;

    public TransactionDetailService(
        TransactionDetailRepository transactionDetailRepository,
        TransactionDetailMapper transactionDetailMapper
    ) {
        this.transactionDetailRepository = transactionDetailRepository;
        this.transactionDetailMapper = transactionDetailMapper;
    }

    /**
     * Save a transactionDetail.
     *
     * @param transactionDetailDTO the entity to save.
     * @return the persisted entity.
     */
    public TransactionDetailDTO save(TransactionDetailDTO transactionDetailDTO) {
        log.debug("Request to save TransactionDetail : {}", transactionDetailDTO);
        TransactionDetail transactionDetail = transactionDetailMapper.toEntity(transactionDetailDTO);
        transactionDetail = transactionDetailRepository.save(transactionDetail);
        return transactionDetailMapper.toDto(transactionDetail);
    }

    /**
     * Update a transactionDetail.
     *
     * @param transactionDetailDTO the entity to save.
     * @return the persisted entity.
     */
    public TransactionDetailDTO update(TransactionDetailDTO transactionDetailDTO) {
        log.debug("Request to update TransactionDetail : {}", transactionDetailDTO);
        TransactionDetail transactionDetail = transactionDetailMapper.toEntity(transactionDetailDTO);
        transactionDetail = transactionDetailRepository.save(transactionDetail);
        return transactionDetailMapper.toDto(transactionDetail);
    }

    /**
     * Partially update a transactionDetail.
     *
     * @param transactionDetailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TransactionDetailDTO> partialUpdate(TransactionDetailDTO transactionDetailDTO) {
        log.debug("Request to partially update TransactionDetail : {}", transactionDetailDTO);

        return transactionDetailRepository
            .findById(transactionDetailDTO.getId())
            .map(existingTransactionDetail -> {
                transactionDetailMapper.partialUpdate(existingTransactionDetail, transactionDetailDTO);

                return existingTransactionDetail;
            })
            .map(transactionDetailRepository::save)
            .map(transactionDetailMapper::toDto);
    }

    /**
     * Get all the transactionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionDetailDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TransactionDetails");
        return transactionDetailRepository.findAll(pageable).map(transactionDetailMapper::toDto);
    }

    /**
     * Get one transactionDetail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransactionDetailDTO> findOne(Long id) {
        log.debug("Request to get TransactionDetail : {}", id);
        return transactionDetailRepository.findById(id).map(transactionDetailMapper::toDto);
    }

    /**
     * Delete the transactionDetail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TransactionDetail : {}", id);
        transactionDetailRepository.deleteById(id);
    }
}
