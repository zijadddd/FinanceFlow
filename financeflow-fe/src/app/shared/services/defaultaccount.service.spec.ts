import { TestBed } from '@angular/core/testing';

import { DefaultaccountService } from './defaultaccount.service';

describe('DefaultaccountService', () => {
  let service: DefaultaccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DefaultaccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
