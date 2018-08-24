import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GrapeListComponent } from './grape-list.component';

describe('GrapeListComponent', () => {
  let component: GrapeListComponent;
  let fixture: ComponentFixture<GrapeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GrapeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GrapeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
