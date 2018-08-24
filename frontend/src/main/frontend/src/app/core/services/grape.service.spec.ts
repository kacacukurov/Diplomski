import { TestBed, inject } from '@angular/core/testing';

import { GrapeService } from './grape.service';

describe('GrapeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GrapeService]
    });
  });

  it('should be created', inject([GrapeService], (service: GrapeService) => {
    expect(service).toBeTruthy();
  }));
});
