import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GrapeModalComponent } from './grape-modal.component';

describe('GrapeModalComponent', () => {
  let component: GrapeModalComponent;
  let fixture: ComponentFixture<GrapeModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GrapeModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GrapeModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
