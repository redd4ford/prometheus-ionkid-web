package com.prometheus.ionkid.service;

import com.prometheus.ionkid.repository.KidRepository;
import com.prometheus.ionkid.model.Kid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class KidService {

  private final KidRepository kidRepository = new KidRepository();

  public List<Kid> getByParentId(String parentDocId)
      throws ExecutionException, InterruptedException {
    return kidRepository.findByParentId(parentDocId);
  }

  public Kid getByName(String parentDocId, String name) throws ExecutionException, InterruptedException {
    return kidRepository.findByName(parentDocId, name);
  }

  public Kid save(String name, Kid kid) {
    return kidRepository.save(name, kid);
  }

  public void deleteById(String programDocId, String name) {
    kidRepository.deleteById(programDocId, name);
  }

}
