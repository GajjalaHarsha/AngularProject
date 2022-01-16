import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentstatusComponent } from './appointmentstatus.component';

describe('AppointmentstatusComponent', () => {
  let component: AppointmentstatusComponent;
  let fixture: ComponentFixture<AppointmentstatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentstatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentstatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
