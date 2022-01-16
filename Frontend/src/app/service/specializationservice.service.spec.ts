import { TestBed } from '@angular/core/testing';

import { SpecializationserviceService } from './specializationservice.service';

describe('SpecializationserviceService', () => {
  let service: SpecializationserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecializationserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
