import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookedappointmentsComponent } from './bookedappointments.component';

describe('BookedappointmentsComponent', () => {
  let component: BookedappointmentsComponent;
  let fixture: ComponentFixture<BookedappointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookedappointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookedappointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
