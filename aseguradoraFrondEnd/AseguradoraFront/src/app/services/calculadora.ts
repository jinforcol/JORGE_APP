import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculadoraService {

  private readonly apiUrl = 'http://localhost:9090/calculadora';

  constructor(private http: HttpClient) {}

  calcular(
    operacion: string,
    numero1: number,
    numero2: number
  ): Observable<number> {

    const url = `${this.apiUrl}/${operacion}/${numero1}/${numero2}`;

    return this.http.get<number>(url);
  }
}