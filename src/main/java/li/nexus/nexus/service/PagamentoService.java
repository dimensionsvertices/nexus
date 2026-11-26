package li.nexus.nexus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import li.nexus.nexus.dto.PagamentoGetDTO;
import li.nexus.nexus.dto.PagamentoPostDTO;
import li.nexus.nexus.entity.Pagamento;
import li.nexus.nexus.exception.NenhumDadoEncontradoException;
import li.nexus.nexus.exception.PagamentoNaoEncontradoException;
import li.nexus.nexus.mapper.PagamentoMapper;
import li.nexus.nexus.repository.PagamentoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;

    @Transactional
    public PagamentoGetDTO savePagamento(PagamentoPostDTO dto) {
        return pagamentoMapper.toGetDTO(pagamentoRepository.save(pagamentoMapper.toEntity(dto)));
    }

    @Transactional
    public List<PagamentoGetDTO> getPagamentos() {
        if (pagamentoRepository.findAll().isEmpty()) {
            throw new NenhumDadoEncontradoException("Nenhum dado cadastrado ou encontrado.");
        }

        return pagamentoMapper.toGetDTOList(pagamentoRepository.findAll());
    }

    @Transactional
    public PagamentoGetDTO getPagamentoById(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado com o ID: " + id);
        }

        return pagamentoMapper.toGetDTO(pagamentoRepository.findById(id).get());
    }

    @Transactional
    public PagamentoGetDTO patchPagamento(Long id, PagamentoPostDTO dto) {
        if (!pagamentoRepository.existsById(id)) {
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado com o ID: " + id);
        }

        Pagamento pagamento = pagamentoRepository.findById(id).get();
        pagamentoMapper.updateEntity(pagamento, dto);
        return pagamentoMapper.toGetDTO(pagamentoRepository.save(pagamento));
    }

    @Transactional
    public void deletePagamentoById(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new PagamentoNaoEncontradoException("Pagamento não encontrado com o ID: " + id);
        }

        pagamentoRepository.delete(pagamentoRepository.findById(id).get());
    }

}
