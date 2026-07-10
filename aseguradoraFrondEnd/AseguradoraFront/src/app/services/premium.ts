import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface PremiumRequest {
  insuredAmount: number;
  age: number;
  coverageType: string;
}

export interface PremiumResponse {
  calculatedPremium: number;
}

@Injectable({
  providedIn: 'root'
})
export class PremiumService {

  private readonly apiUrl =
    'http://localhost:9090/premium/calculate';

  constructor(private http: HttpClient) {}

  calculatePremium(
    request: PremiumRequest
  ): Observable<PremiumResponse> {

    return this.http.post<PremiumResponse>(
      this.apiUrl,
      request
    );
  }
}