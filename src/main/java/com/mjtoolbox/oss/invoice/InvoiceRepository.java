package com.mjtoolbox.oss.invoice;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {
}
