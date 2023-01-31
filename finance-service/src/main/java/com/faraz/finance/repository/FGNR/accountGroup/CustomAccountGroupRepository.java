package com.faraz.finance.repository.FGNR.accountGroup;

import com.faraz.finance.controller.FGNR.dto.CompleteAccountListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomAccountGroupRepository {


    private final JdbcTemplate jdbcTemplate;

    public Optional<List<CompleteAccountListDTO>> getCompleteAccountList(Integer companyId) {

        final String sql = "SELECT ag.ID accountGroupId,l.ID ledgerId,sdl.ID subsidiaryLedgerId,ag.CODE accountGroupCode," +
                "ag.FATITLE accountGroupFaTitle,ag.ENTITLE accountGroupEnTitle,agt.FATITLE accountGroupTypeFaTitle," +
                "agt.ENTITLE accountGroupTypeEnTitle,l.CODE ledgerCode,l.FATITLE ledgerFaTitle,l.ENTITLE ledgerEnTitle," +
                "sdl.CODE subsidiaryLedgerCode, sdl.FATITLE subsidiaryLedgerFaTitle ,sdl.ENTITLE subsidiaryLedgerEnTitle," +
                " an.FATITLE subsidiaryLedgerNatureFaTitle,an.ENTITLE subsidiaryLedgerNatureEnTitle, sdl.ISACTIVE subsidiaryLedgerIsActive" +
                " FROM FGNR.ACCOUNTGROUP ag RIGHT OUTER JOIN FGNR.LEDGER l ON ag.ID = l.ACCOUNTGROUPID" +
                " RIGHT OUTER JOIN FGNR.SUBSIDIARYLEDGER sdl ON l.ID = sdl.LEDGERID " +
                " INNER Join FSYS.ACCOUNTGROUPTYPE agt ON ag.ACCOUNTGROUPTYPEID=agt.ID " +
                " INNER Join FSYS.ACCOUNTNATURE an ON sdl.ACCOUNTNATUREID=an.ID WHERE" +
                " ag.ISDELETED=0 AND l.ISDELETED=0 AND sdl.ISDELETED=0 AND ag.COMPANYID=" + companyId;

        return Optional.of(
                jdbcTemplate.query(
                        sql, new RowMapper<>() {
                            @Override
                            public CompleteAccountListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                                return CompleteAccountListDTO.builder()
                                        .accountGroupId(rs.getInt(1))
                                        .ledgerId(rs.getInt(2))
                                        .subsidiaryLedgerId(rs.getInt(3))
                                        .accountGroupCode(rs.getInt(4))
                                        .accountGroupFaTitle(rs.getString(5))
                                        .accountGroupEnTitle(rs.getString(6))
                                        .accountGroupTypeFaTitle(rs.getString(7))
                                        .accountGroupTypeEnTitle(rs.getString(8))
                                        .ledgerCode(rs.getInt(9))
                                        .ledgerFaTitle(rs.getString(10))
                                        .ledgerEnTitle(rs.getString(11))
                                        .subsidiaryLedgerCode(rs.getInt(12))
                                        .subsidiaryLedgerFaTitle(rs.getString(13))
                                        .subsidiaryLedgerEnTitle(rs.getString(14))
                                        .subsidiaryLedgerNatureFaTitle(rs.getString(15))
                                        .subsidiaryLedgerNatureEnTitle(rs.getString(16))
                                        .subsidiaryLedgerIsActive(rs.getBoolean(17))
                                        .build();
                            }
                        }
                )
        );

    }

}
