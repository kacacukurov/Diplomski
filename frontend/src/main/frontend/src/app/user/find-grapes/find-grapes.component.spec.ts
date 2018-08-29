import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindGrapesComponent } from './find-grapes.component';

describe('FindGrapesComponent', () => {
  let component: FindGrapesComponent;
  let fixture: ComponentFixture<FindGrapesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindGrapesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindGrapesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
