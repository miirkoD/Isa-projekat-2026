import { TestBed } from '@angular/core/testing';

import { IznajmljivanjeService } from './iznajmljivanje-service';

describe('IznajmljivanjeService', () => {
  let service: IznajmljivanjeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IznajmljivanjeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
