package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Location;
import model.entities.ModelVehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String modelCar = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		Location loc = new Location(start, finish, new ModelVehicle(modelCar));
		
		System.out.print("Entre com o pre�o por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o pre�o por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(loc);
		
		System.out.println();
		System.out.println("FATURA:");
		System.out.println("Pagamento b�sico: " + String.format("%.2f", loc.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", loc.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", loc.getInvoice().getTotalPayment()));
		
		sc.close();

	}

}
