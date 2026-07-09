import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-calculadora',
  imports: [FormsModule],
  templateUrl: './calculadora.html',
  styleUrl: './calculadora.css',
})
export class Calculadora {
    num1: number = 0;
    num2: number = 0;
    resultado: number = 0;

    //metodo para sumar dos numeros
    sumar() {
        this.resultado = this.num1 + this.num2;
    }

}
