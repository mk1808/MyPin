import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { mapDetailsGuard } from './map-details.guard';

describe('mapDetailsGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => mapDetailsGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
