package com.zeylin.eventsourcingwithcqrs.entities;

import com.zeylin.eventsourcingwithcqrs.repositories.ShowtimeQueryEntityRepository;
import com.zeylin.eventsourcingwithcqrs.utils.ObjectToPayloadConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShowtimeQueryEntityManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowtimeQueryEntityManager.class);

    private final ShowtimeQueryEntityRepository showtimeQueryEntityRepository;

    public ShowtimeQueryEntityManager(ShowtimeQueryEntityRepository showtimeQueryEntityRepository,
                             ObjectToPayloadConverter converter) {
        this.showtimeQueryEntityRepository = showtimeQueryEntityRepository;
    }

    void on(BaseEvent event){
        persistAccount(buildQueryAccount(getAccountFromEvent(event)));
    }


    private AccountAggregate getAccountFromEvent(BaseEvent event){
        return accountAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private AccountQueryEntity findExistingOrCreateQueryAccount(String id){
        return accountRepository.findById(id).isPresent() ? accountRepository.findById(id).get() : new AccountQueryEntity();
    }

    private AccountQueryEntity buildQueryAccount(AccountAggregate accountAggregate){
        AccountQueryEntity accountQueryEntity = findExistingOrCreateQueryAccount(accountAggregate.getId());

        accountQueryEntity.setId(accountAggregate.getId());
        accountQueryEntity.setAccountBalance(accountAggregate.getAccountBalance());
        accountQueryEntity.setCurrency(accountAggregate.getCurrency());
        accountQueryEntity.setStatus(accountAggregate.getStatus());

        return accountQueryEntity;
    }

    private void persistAccount(ShowtimeQueryEntity accountQueryEntity){
        showtimeQueryEntityRepository.save(accountQueryEntity);
    }

}
