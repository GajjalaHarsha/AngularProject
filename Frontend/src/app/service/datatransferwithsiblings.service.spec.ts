import { TestBed } from '@angular/core/testing';

import { DatatransferwithsiblingsService } from './datatransferwithsiblings.service';

describe('DatatransferwithsiblingsService', () => {
  let service: DatatransferwithsiblingsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatatransferwithsiblingsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
