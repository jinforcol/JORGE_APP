import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CalculadoraService } from '../../services/calculadora';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-calculadora',
  imports: [FormsModule, CommonModule],
  templateUrl: './calculadora.html',
  styleUrl: './calculadora.css',
})
export class Calculadora {
  num1: number = 0;
  num2: number = 0;
  resultado: number = 0;
  mensajeError: string = '';
  constructor(private CalculadoraService: CalculadoraService) { }

  //metodo para sumar dos numeros
  sumar() {
    this.CalculadoraService.calcular('sumar', this.num1, this.num2).subscribe({
      next: (respuesta) => { this.resultado = respuesta; this.mensajeError = '';  },
      
    });
  }

  restar(){
    this.CalculadoraService.calcular('restar', this.num1, this.num2).subscribe({
      next: (respuesta) => { this.resultado = respuesta; this.mensajeError = '';    }
    });
  }

  multiplicar(){
    this.CalculadoraService.calcular('multiplicar', this.num1, this.num2).subscribe({
      next: (respuesta) => { this.resultado = respuesta; this.mensajeError = '';      }
    });
  }

  dividir(){
    this.CalculadoraService.calcular('dividir', this.num1, this.num2).subscribe({
      next:  (respuesta) => {this.resultado = respuesta; this.mensajeError = '';},
      error: (error) => {this.mensajeError ='No se puede dividir entre cero';},
    });
  }
}