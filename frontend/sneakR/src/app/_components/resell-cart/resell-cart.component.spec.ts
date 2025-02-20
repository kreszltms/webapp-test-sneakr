import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResellCartComponent } from './resell-cart.component';

describe('ResellCartComponent', () => {
  let component: ResellCartComponent;
  let fixture: ComponentFixture<ResellCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResellCartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResellCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
