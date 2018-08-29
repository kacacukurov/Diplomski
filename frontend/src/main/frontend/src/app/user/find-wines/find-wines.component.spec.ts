import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindWinesComponent } from './find-wines.component';

describe('FindWinesComponent', () => {
  let component: FindWinesComponent;
  let fixture: ComponentFixture<FindWinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindWinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindWinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
