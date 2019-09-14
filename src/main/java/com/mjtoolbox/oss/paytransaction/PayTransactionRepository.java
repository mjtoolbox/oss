package com.mjtoolbox.oss.paytransaction;

import com.mjtoolbox.oss.invoice.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PayTransactionRepository extends PagingAndSortingRepository<PayTransaction, Long> {
    List<PayTransaction> findByInvoice(Invoice invoice);
}
