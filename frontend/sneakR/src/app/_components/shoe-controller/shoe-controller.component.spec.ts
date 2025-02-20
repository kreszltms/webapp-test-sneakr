import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoeControllerComponent } from './shoe-controller.component';

describe('ShoeControllerComponent', () => {
  let component: ShoeControllerComponent;
  let fixture: ComponentFixture<ShoeControllerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShoeControllerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShoeControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
