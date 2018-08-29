import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindWinesAndGrapesComponent } from './find-wines-and-grapes.component';

describe('FindWinesAndGrapesComponent', () => {
  let component: FindWinesAndGrapesComponent;
  let fixture: ComponentFixture<FindWinesAndGrapesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindWinesAndGrapesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindWinesAndGrapesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
