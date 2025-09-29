package com.example.cardatabase4.service;

import com.example.cardatabase4.domain.Car;
import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.domain.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // 1. Owner 전체 조회
    public List<Owner> getOwner(){
        return ownerRepository.findAll();
    }

    // 2. id 별 조회
    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    // 3. Owner 객체 추가
    public Owner addOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    // 4. Owner 객체 삭제
    public boolean deleteOwner(Long id){
        if(ownerRepository.existsById(id)){
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 5. Owner 객체 수정
    @Transactional
    public Optional<Owner> updateOwner(Long id, Owner ownerDetails){
        return ownerRepository.findById(id)
                .map(owner -> {
                    owner.setCars(ownerDetails.getCars());
                    owner.setLastName(ownerDetails.getLastName());
                    owner.setFirstName(ownerDetails.getFirstName());
                    return owner;
                });
    }
}
