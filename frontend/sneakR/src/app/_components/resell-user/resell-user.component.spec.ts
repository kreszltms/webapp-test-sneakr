import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResellUserComponent } from './resell-user.component';

describe('ResellUserComponent', () => {
  let component: ResellUserComponent;
  let fixture: ComponentFixture<ResellUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResellUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResellUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
