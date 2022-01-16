import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentbookingComponent } from './appointmentbooking.component';

describe('AppointmentbookingComponent', () => {
  let component: AppointmentbookingComponent;
  let fixture: ComponentFixture<AppointmentbookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentbookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
