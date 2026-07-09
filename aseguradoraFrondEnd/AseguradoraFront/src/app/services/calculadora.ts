import { Service } from '@angular/core';

@Service()
export class Calculadora {
    //metodo para sumar dos numeros
    sumar(num1: number, num2: number): number {
        return num1 + num2;
    }
    
}
