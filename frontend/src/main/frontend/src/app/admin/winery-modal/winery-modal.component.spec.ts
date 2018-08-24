import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WineryModalComponent } from './winery-modal.component';

describe('WineryModalComponent', () => {
  let component: WineryModalComponent;
  let fixture: ComponentFixture<WineryModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WineryModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WineryModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
