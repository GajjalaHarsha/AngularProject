import { TestBed } from '@angular/core/testing';

import { TransferwithsiblingService } from './transferwithsibling.service';

describe('TransferwithsiblingService', () => {
  let service: TransferwithsiblingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransferwithsiblingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
