import { TestBed } from '@angular/core/testing';

import { PinListsApiService } from './pin-lists-api.service';

describe('PinListsApiService', () => {
  let service: PinListsApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PinListsApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
