package codility;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on:  May 23, 2021
 * Questions: https://app.codility.com/test/W9GDEG-XW2/ (Build a transaction processor using Java 8 Streams.)
 */

public class Reconcile {

    public static void main(String[] args) {

    }

    Stream<PendingTransaction> reconcile(Stream<PendingTransaction> pending, Stream<Stream<ProcessedTransaction>> processed) {
        if (pending == null || processed == null) return Stream.empty();
        Set<String> processedIds = new HashSet<>();
        flatProcessed(processed, processedIds);
        return pending.filter(pendingTran -> isProcessed(pendingTran, processedIds));
    }

    /*
        This method is to filter the PendingTransaction and return true when processed.
    */
    private boolean isProcessed(PendingTransaction pendingTran, Set<String> processedIds) {
        String pendingTranId = String.valueOf(pendingTran.getId());
        return processedIds.contains(pendingTranId);
    }

    /*
        This methods flattens the stream of stream to set of processed transaction, that has status as DONE.
    */
    private void flatProcessed(Stream<Stream<ProcessedTransaction>> processed, Set<String> processedIds) {
        processed.forEach(processedStream -> Optional.ofNullable(processedStream)
                        .ifPresent(nonNullStream -> nonNullStream
                                .filter(this::processedFilter)
                                .map(ProcessedTransaction::getId)
                                .forEach(processedIds::add))
                );
    }

    private boolean processedFilter(ProcessedTransaction processedTransaction) {
        Optional<String> status = processedTransaction.getStatus();
        String transactionId = processedTransaction.getId();
        return status.orElse("").equalsIgnoreCase("DONE") && transactionId != null;
    }

    class PendingTransaction {
        Long id;
        public Long getId() {
            return id;
        }
    }

    class ProcessedTransaction {
        String id;
        Optional<String> status;
        public String getId() {
            return id;
        }
        public Optional<String> getStatus() {
            return status;
        }
    }
}
