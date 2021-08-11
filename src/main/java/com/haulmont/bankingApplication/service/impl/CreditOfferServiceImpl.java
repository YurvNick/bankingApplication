package com.haulmont.bankingApplication.service.impl;

import com.haulmont.bankingApplication.models.CreditOffer;
import com.haulmont.bankingApplication.models.Payment;
import com.haulmont.bankingApplication.repository.CreditOfferRepository;
import com.haulmont.bankingApplication.service.CrudEntityService;
import org.springframework.stereotype.Service;
import java.util.Collection;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class CreditOfferServiceImpl implements CrudEntityService<CreditOffer> {

    private final CreditOfferRepository creditOfferRepository;

    public CreditOfferServiceImpl(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    @Transactional
    public CreditOffer create(CreditOffer creditOffer) { return creditOfferRepository.save(creditOffer);
    }

    @Override
    public List<CreditOffer> getAll() {
        return creditOfferRepository.findAll();
    }

    @Override
    public CreditOffer getByUuid(UUID uuid) {
        return creditOfferRepository.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void delete(CreditOffer creditOffer) {
        creditOfferRepository.delete(creditOffer);
    }

    public void generatePaymentList(CreditOffer creditOffer) {
        float p = creditOffer.getCredit().getInterestRate() / 12;
        long monthlyPayment = (long) Math.round(creditOffer.getAmount() * (p + (p / (Math.pow((1 + p), creditOffer.getCreditTerm()) - 1))));
        long interestTotal = 0;
        long summary = creditOffer.getAmount();
        List<Payment> paymentList = creditOffer.getPaymentList();
        paymentList.clear();
        for (int i = 0; i < creditOffer.getCreditTerm(); i++) {
            long interestAmount = (long) Math.floor(summary * p);
            long principalAmount = monthlyPayment - interestAmount;
            summary -= principalAmount;
            paymentList.add(new Payment(
                    UUID.randomUUID(),
                    creditOffer.getDate().plusMonths(i),
                    monthlyPayment,
                    principalAmount,
                    interestAmount,
                    creditOffer));
            interestTotal += interestAmount;
        }
        creditOffer.setInterestTotal(interestTotal);
        creditOffer.setPaymentList(paymentList);
    }
}
