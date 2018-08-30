import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutWineModalComponent } from './about-wine-modal.component';

describe('AboutWineModalComponent', () => {
  let component: AboutWineModalComponent;
  let fixture: ComponentFixture<AboutWineModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AboutWineModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutWineModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
