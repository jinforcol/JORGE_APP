import { ChangeDetectorRef, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { PremiumService } from '../../services/premium';

@Component({
  selector: 'app-policy',
  imports: [FormsModule],
  templateUrl: './policy.html',
  styleUrl: './policy.css',
})
export class Policy {
  insuredAmount: number = 0;
  age: number = 18;
  coverageType: string = 'basic';
  calculatedPremium: number = 0;
  errorMessage: string = '';

  constructor(
    private premiumService: PremiumService,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  calculatePremium() {
  const request = {
    insuredAmount: this.insuredAmount,
    age: this.age,
    coverageType: this.coverageType
  };

  this.errorMessage = '';


  this.premiumService
    .calculatePremium(request)
    .subscribe({
      next: (response) => {
        this.calculatedPremium =
          response.calculatedPremium;
        this.errorMessage = '';
        this.changeDetectorRef.detectChanges();
      },
      error: (error: HttpErrorResponse) => {
        this.errorMessage =
          error.error?.message ||
          'No fue posible calcular la prima. Verifica los datos ingresados.';
        this.changeDetectorRef.detectChanges();
      }
    });
}
}
