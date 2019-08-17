import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimilarWinesModalComponent } from './similar-wines-modal.component';

describe('SimilarWinesModalComponent', () => {
  let component: SimilarWinesModalComponent;
  let fixture: ComponentFixture<SimilarWinesModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimilarWinesModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimilarWinesModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
