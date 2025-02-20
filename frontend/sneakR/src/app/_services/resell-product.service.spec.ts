import { TestBed } from '@angular/core/testing';

import { ResellProductService } from './resell-product.service';

describe('ResellProductService', () => {
  let service: ResellProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResellProductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
