import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterWinesComponent } from './filter-wines.component';

describe('FilterWinesComponent', () => {
  let component: FilterWinesComponent;
  let fixture: ComponentFixture<FilterWinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilterWinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterWinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
