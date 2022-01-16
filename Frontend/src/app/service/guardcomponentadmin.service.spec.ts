import { TestBed } from '@angular/core/testing';

import { GuardcomponentadminService } from './guardcomponentadmin.service';

describe('GuardcomponentadminService', () => {
  let service: GuardcomponentadminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardcomponentadminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
