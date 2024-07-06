package com.tech.home.app.web.rest;

import com.tech.home.app.repository.TransactionDetailRepository;
import com.tech.home.app.service.TransactionDetailService;
import com.tech.home.app.service.dto.TransactionDetailDTO;
import com.tech.home.app.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.tech.home.app.web.util.PaginationUtil;
import com.tech.home.app.web.util.ResponseUtil;
import com.tech.home.app.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tech.home.app.domain.TransactionDetail}.
 */
@RestController
@RequestMapping("/api/transaction-details")
public class TransactionDetailResource {

    private static final Logger log = LoggerFactory.getLogger(TransactionDetailResource.class);

    private static final String ENTITY_NAME = "transactionDetail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionDetailService transactionDetailService;

    private final TransactionDetailRepository transactionDetailRepository;

    public TransactionDetailResource(
        TransactionDetailService transactionDetailService,
        TransactionDetailRepository transactionDetailRepository
    ) {
        this.transactionDetailService = transactionDetailService;
        this.transactionDetailRepository = transactionDetailRepository;
    }

    /**
     * {@code POST  /transaction-details} : Create a new transactionDetail.
     *
     * @param transactionDetailDTO the transactionDetailDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transactionDetailDTO, or with status {@code 400 (Bad Request)} if the transactionDetail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TransactionDetailDTO> createTransactionDetail(@RequestBody TransactionDetailDTO transactionDetailDTO)
        throws URISyntaxException {
        log.debug("REST request to save TransactionDetail : {}", transactionDetailDTO);
        if (transactionDetailDTO.getId() != null) {
            throw new BadRequestAlertException("A new transactionDetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        transactionDetailDTO = transactionDetailService.save(transactionDetailDTO);
        return ResponseEntity.created(new URI("/api/transaction-details/" + transactionDetailDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, transactionDetailDTO.getId().toString()))
            .body(transactionDetailDTO);
    }

    /**
     * {@code PUT  /transaction-details/:id} : Updates an existing transactionDetail.
     *
     * @param id the id of the transactionDetailDTO to save.
     * @param transactionDetailDTO the transactionDetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionDetailDTO,
     * or with status {@code 400 (Bad Request)} if the transactionDetailDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transactionDetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDetailDTO> updateTransactionDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransactionDetailDTO transactionDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TransactionDetail : {}, {}", id, transactionDetailDTO);
        if (transactionDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactionDetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        transactionDetailDTO = transactionDetailService.update(transactionDetailDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transactionDetailDTO.getId().toString()))
            .body(transactionDetailDTO);
    }

    /**
     * {@code PATCH  /transaction-details/:id} : Partial updates given fields of an existing transactionDetail, field will ignore if it is null
     *
     * @param id the id of the transactionDetailDTO to save.
     * @param transactionDetailDTO the transactionDetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transactionDetailDTO,
     * or with status {@code 400 (Bad Request)} if the transactionDetailDTO is not valid,
     * or with status {@code 404 (Not Found)} if the transactionDetailDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the transactionDetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TransactionDetailDTO> partialUpdateTransactionDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransactionDetailDTO transactionDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TransactionDetail partially : {}, {}", id, transactionDetailDTO);
        if (transactionDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transactionDetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transactionDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TransactionDetailDTO> result = transactionDetailService.partialUpdate(transactionDetailDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transactionDetailDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /transaction-details} : get all the transactionDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactionDetails in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TransactionDetailDTO>> getAllTransactionDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TransactionDetails");
        Page<TransactionDetailDTO> page = transactionDetailService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transaction-details/:id} : get the "id" transactionDetail.
     *
     * @param id the id of the transactionDetailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transactionDetailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailDTO> getTransactionDetail(@PathVariable("id") Long id) {
        log.debug("REST request to get TransactionDetail : {}", id);
        Optional<TransactionDetailDTO> transactionDetailDTO = transactionDetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transactionDetailDTO);
    }

    /**
     * {@code DELETE  /transaction-details/:id} : delete the "id" transactionDetail.
     *
     * @param id the id of the transactionDetailDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionDetail(@PathVariable("id") Long id) {
        log.debug("REST request to delete TransactionDetail : {}", id);
        transactionDetailService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
