import { TestBed } from '@angular/core/testing';

import { PrimerakService } from './primerak-service';

describe('PrimerakService', () => {
  let service: PrimerakService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrimerakService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
