import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmindoctorseditComponent } from './admindoctorsedit.component';

describe('AdmindoctorseditComponent', () => {
  let component: AdmindoctorseditComponent;
  let fixture: ComponentFixture<AdmindoctorseditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdmindoctorseditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmindoctorseditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
