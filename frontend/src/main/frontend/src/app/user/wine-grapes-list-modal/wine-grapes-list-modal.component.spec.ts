import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WineGrapesListModalComponent } from './wine-grapes-list-modal.component';

describe('WineGrapesListModalComponent', () => {
  let component: WineGrapesListModalComponent;
  let fixture: ComponentFixture<WineGrapesListModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WineGrapesListModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WineGrapesListModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
