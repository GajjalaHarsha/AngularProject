import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorviewComponent } from './doctorview.component';

describe('DoctorviewComponent', () => {
  let component: DoctorviewComponent;
  let fixture: ComponentFixture<DoctorviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
