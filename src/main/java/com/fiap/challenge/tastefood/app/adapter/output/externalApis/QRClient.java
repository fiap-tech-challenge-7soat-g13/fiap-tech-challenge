package com.fiap.challenge.tastefood.app.adapter.output.externalApis;

import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.MercadoPagoRequestDTO;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto.MercadoPagoResponseDTO;

public interface QRClient {

	MercadoPagoResponseDTO generateQRCode(MercadoPagoRequestDTO dto);

}
