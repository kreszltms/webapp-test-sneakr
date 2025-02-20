import { TestBed } from '@angular/core/testing';

import { ResellCartService } from './resell-cart.service';

describe('ResellCartService', () => {
  let service: ResellCartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResellCartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
