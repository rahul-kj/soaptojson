package com.proj.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;

import com.proj.domain.LoanGeneral;
import com.proj.domain.LoanResponse;

@Service
public class LoanResponseService {

	static String FILE_PATH = "src/main/resources/response.xml";

	public static void main(String[] args) throws Exception {
		new LoanResponseService().getResponse();
	}

	public LoanResponse getResponse() throws Exception {
		XMLInputFactory xif = XMLInputFactory.newFactory();
		StreamSource ss = new StreamSource(FILE_PATH);
		XMLStreamReader r = xif.createXMLStreamReader(ss);

		do {
			r.nextTag();
		} while (!r.getLocalName().equals("return"));

		JAXBContext jaxbContext = JAXBContext.newInstance(LoanResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<LoanResponse> je = unmarshaller.unmarshal(r, LoanResponse.class);

		
        LoanResponse loanResponse = je.getValue();
        
		LoanGeneral loanGeneral = loanResponse.getLoanGeneral();
		System.out.println(loanGeneral.getArm2FixedConversionFeature());
        System.out.println(loanGeneral.getLoanOriginalTerm());
		System.out.println(loanGeneral.getInterestCode());
		System.out.println(loanGeneral.getLoanOriginalUPBAmount());
		System.out.println(loanGeneral.getLoanSaleTypeCode());
		System.out.println(loanGeneral.getServicerLoanNumber());
		
		return loanResponse;
	}
}
