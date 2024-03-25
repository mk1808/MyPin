import { TestBed } from '@angular/core/testing';

import { SynchronizationApiService } from './synchronization-api.service';

describe('SynchronizationApiService', () => {
  let service: SynchronizationApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SynchronizationApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
